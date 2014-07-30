/*******************************************************************************
 *
 *	Copyright (C) 2008, 2009 Fujitsu Services Ltd.
 *
 *	Author: Nick Battle
 *
 *	This file is part of VDMJ.
 *
 *	VDMJ is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	VDMJ is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with VDMJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 ******************************************************************************/

package org.overture.interpreter.traces;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.overture.ast.factory.AstFactory;
import org.overture.ast.lex.LexLocation;

public class RepeatTraceNode extends TraceNode
{
	public final TraceNode repeat;
	public final int from;
	public final int to;

	public RepeatTraceNode(TraceNode repeat, long from, long to)
	{
		this.repeat = repeat;
		this.from = (int) from;
		this.to = (int) to;
	}

	@Override
	public String toString()
	{
		return repeat.toString()
				+ ((from == 1 && to == 1) ? ""
						: (from == to) ? ("{" + from + "}") : ("{" + from
								+ ", " + to + "}"));
	}

	@Override
	public TestSequence getTests()
	{
		return new LazyTestSequence(this);

		// TestSequence tests = new TestSequence();
		// TestSequence rtests = repeat.getTests();
		// int count = rtests.size();
		//
		// for (int r = from; r <= to; r++)
		// {
		// if (r == 0)
		// {
		// CallSequence seq = getVariables();
		// seq.add(AstFactory.newASkipStm(new LexLocation()));
		// tests.add(seq);
		// continue;
		// }
		//
		// int[] c = new int[r];
		//
		// for (int i=0; i<r; i++)
		// {
		// c[i] = count;
		// }
		//
		// Permutor p = new Permutor(c);
		//
		// while (p.hasNext())
		// {
		// CallSequence seq = getVariables();
		// int[] select = p.next();
		//
		// for (int i=0; i<r; i++)
		// {
		// seq.addAll(rtests.get(select[i]));
		// }
		//
		// tests.add(seq);
		// }
		// }
		//
		// return tests;
	}

	public class LazyTestSequence extends TestSequence
	{

		/**
		 * serial
		 */
		private static final long serialVersionUID = 1L;
		private RepeatTraceNode repeatTraceNode;

		public LazyTestSequence(RepeatTraceNode repeatTraceNode)
		{
			this.repeatTraceNode = repeatTraceNode;
		}

		@Override
		public synchronized int size()
		{
			return this.repeatTraceNode.size2();
		}

		@Override
		public synchronized CallSequence get(int index)
		{
			return this.repeatTraceNode.get(index);
		}

		@Override
		public synchronized Iterator<CallSequence> iterator()
		{
			return new Iterator<CallSequence>()
			{
				int index = 0;

				@Override
				public boolean hasNext()
				{
					return index < LazyTestSequence.this.size();
				}

				@Override
				public CallSequence next()
				{
					return LazyTestSequence.this.get(index++);
				}

				@Override
				public void remove()
				{

				}
			};
		}
	}

	public CallSequence get(int index)
	{
		if (indics == null)
		{
			size2();
		}
		Pair<Integer, Integer[]> v = indics.get(index);

		int r = v.first;
		Integer[] select = v.second;

		if (r == 0)
		{
			CallSequence seq = getVariables();
			seq.add(AstFactory.newASkipStm(new LexLocation()));
			return seq;

		} else
		{
			CallSequence seq = getVariables();
			TestSequence rtests = repeat.getTests();

			for (int i = 0; i < r; i++)
			{
				seq.addAll(rtests.get(select[i]));
			}
			return seq;
		}

	}

	public int size()
	{
		return (1 + to - from) * repeat.getTests().size();

	}

	class Pair<T1, T2>
	{
		public Pair(T1 first, T2 second)
		{
			this.first = first;
			this.second = second;
		}

		public T1 first;
		public T2 second;

		@Override
		public String toString()
		{
			return "(" + first + ":" + second + ")";
		}
	}

	Map<Integer, Pair<Integer, Integer[]>> indics;

	public int size2()
	{
		indics = new HashMap<Integer, Pair<Integer, Integer[]>>();
		int size = 0;
		TestSequence rtests = repeat.getTests();
		int count = rtests.size();
		for (int r = from; r <= to; r++)
		{
			if (r == 0)
			{

				indics.put(size, new Pair<Integer, Integer[]>(r, new Integer[] {}));
				size++;
				continue;
			}

			int[] c = new int[r];

			for (int i = 0; i < r; i++)
			{
				c[i] = count;
			}

			Permutor p = new Permutor(c);

			while (p.hasNext())
			{
				int[] next = p.next();
				Integer[] select = new Integer[next.length];
				for (int i = 0; i < next.length; i++)
				{
					select[i] = next[i];
				}

				indics.put(size, new Pair<Integer, Integer[]>(r, select));
				size++;
			}
		}

		System.out.println(indics);
		return size;
	}

	int read = 0;
	int r = -1;
	CallSequence toRepeat = null;

	@Override
	public boolean hasNext()
	{
		return read < 1 + to - from || this.repeat.hasNext();
	}

	@Override
	public CallSequence getNext()
	{
		if (!hasNext())
		{
			return null;
		}

		if (r == -1 || read > to)
		{
			read = 0;
			r = from;
			toRepeat = this.repeat.getNext();
		}

		CallSequence seq = getVariables();
		for (int i = r; i <= to; i++)
		{
			seq.addAll(toRepeat);
		}

		r++;
		read++;

		return seq;
	}
}
